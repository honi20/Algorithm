#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;
typedef long long ll;

int dy[4] = {1,-1,0,0};
int dx[4] = {0,0,-1,1};

int n,m;
int arr[1002][1002];
bool visited[1002][1002];

void solve(int y, int x){
    visited[y][x] = true;
    arr[y][x] = 2;
    
    for (int k=0;k<4;k++){
        int ny = y + dy[k];
        int nx = x + dx[k];
        
        if (ny >= n || nx >= m || ny < 0 || nx < 0)
            continue;
        
        if (!visited[ny][nx] && arr[ny][nx] == 0)
            solve(ny, nx);
    }
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    string x;
    
    cin>>n>>m;
    for (int i=0;i<n;i++){
        cin>>x;
        for (int j=0;j<m;j++){
            arr[i][j] = x[j]-'0';
        }
    }
    
    for (int j=0; j<m; j++){
        if (arr[0][j] == 0 && !visited[0][j]){
            solve(0, j);
        }
    }
    
    bool result = false;
    for (int j=0;j<m;j++){
        if (arr[n-1][j] == 2){
            result = true;
            break;
        }
    }
    if (result)
        cout<<"YES";
    else
        cout<<"NO";
    
}
