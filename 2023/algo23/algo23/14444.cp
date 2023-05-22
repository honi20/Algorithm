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

int A[200002];
int S[200002];
string str, str2;

void manachers(string str){
    
    int r=0, p=0;
    int len = str.size();
    
    for (int i=0; i<len; i++){
        if (i<=r)
            A[i] = min(A[2*p-i], r-i);
        else
            A[i] = 0;
        
        while (i-A[i]-1 >= 0 && i+A[i]+1 < len && str2[i-A[i]-1] == str2[i+A[i]+1])
            A[i]++;
        
        if (r < i+A[i]){
            r = i + A[i];
            p = i;
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    cin>>str;
    for (int i=0; i<str.size(); i++){
        str2 += "#";
        str2 += str[i];
    }
    str2 += "#";
    
    manachers(str2);
    int maxVal = -1;
    for (int i=0; i<str2.size(); i++){
        maxVal = max(maxVal, A[i]);
    }
    cout<<maxVal;
}

