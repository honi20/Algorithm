#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int n, m;
int s, e;
int A[2000002];
int S[2000002];

void manachers(){
    int r=0, p=0;
    for (int i=0; i<n; i++){
        if (i<=r)
            A[i] = min(A[2*p-i], r-i);
        else
            A[i] = 0;
        
        while (i-A[i]-1 >= 0 && i+A[i]+1 < n && S[i-A[i]-1] == S[i+A[i]+1])
            A[i] += 1;
        
        if (r < i+A[i]){
            r = i + A[i];
            p = i;
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>S[i*2+1];
        S[i*2] = 0;
    }
    
    n = n*2+1;
    S[n-1] = 0;
    
    manachers();
    
    cin>>m;
    while (m--){
        cin>>s>>e;
        s--; e--;
        
        if (A[s+e+1] >= e-s+1)
            cout<<"1\n";
        else
            cout<<"0\n";
    }
}

