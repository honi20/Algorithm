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
int arr[10] = {0,};
int result[10] = {0,};
bool visited[10];

void solve(int cnt){
    if (cnt == m){
        for (int i=0; i<m; i++)
            cout<<result[i]<<" ";
        cout<<"\n";
        return;
    }
    
    int tmp = 0;
    for (int i=0; i<n; i++){
        if (!visited[i] && arr[i] != tmp){
            result[cnt] = arr[i];
            tmp = result[cnt];
            visited[i] = true;
            solve(cnt+1);
            visited[i] = false;
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0; i<n; i++)
        cin>>arr[i];
    
    sort(arr, arr+n);
    solve(0);
}

