#include <iostream>
using namespace std;

int n;
char x;
int arr[102][102];
bool visited[102][102];
int check = 0;
int result1=0, result2=0;

int dy[4]={1,-1,0,0};
int dx[4]={0,0,1,-1};

void dfs(int y, int x){
    visited[y][x]=true;
    
    for (int k=0;k<4;k++){
        int ny = y+dy[k];
        int nx = x+dx[k];
        
        if (ny<0 || nx<0 || ny>=n || nx>=n)
            continue;
        
        if (!visited[ny][nx]){
            if (check == 0){
                if (arr[ny][nx] == arr[y][x]){
                    dfs(ny,nx);
                }
            }
            
            else if (check == 1){
                if (arr[ny][nx] == arr[y][x] || (arr[ny][nx]+arr[y][x] == 3 && arr[y][x]!=3))
                    dfs(ny,nx);
            }
        }
    }
    
}


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=0;i<n;i++){
        for (int j=0;j<n;j++){
            cin>>x;
            if (x == 'R')
                arr[i][j] = 1;
            else if (x == 'G')
                arr[i][j] = 2;
            else if (x == 'B')
                arr[i][j] = 3;
        }
    }
    
    for (int i=0;i<n;i++){
        for (int j=0;j<n;j++){
            if (!visited[i][j]){
                dfs(i,j);
                result1++;
            }
        }
    }
    
    for (int i=0;i<n;i++){
        for (int j=0;j<n;j++){
            visited[i][j]=false;
        }
    }
    check=1;
    for (int i=0;i<n;i++){
        for (int j=0;j<n;j++){
            if (!visited[i][j]){
                dfs(i,j);
                result2++;
            }
        }
    }
    cout<<result1<<" "<<result2;
    
}
