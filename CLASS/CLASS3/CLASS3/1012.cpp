#include <iostream>
#include <vector>
using namespace std;

int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,1,-1};

int t,m,n,k;
int x,y;
int arr[55][55];
bool visited[55][55];
int result = 0;
int cnt = 0;

void dfs(int y, int x){
    visited[y][x] = true;
    cnt++;
    
    for (int i=0;i<4;i++){
        int nexty = y+dy[i];
        int nextx = x+dx[i];
        
        if (nexty<0 || nextx<0 || nexty>=n || nextx>=m)
            continue;
        
        if (!visited[nexty][nextx] && arr[nexty][nextx]==1){
            dfs(nexty,nextx);
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>t;
    while (t--){
        cin>>m>>n>>k;
        
        vector <pair<int,int>> cab;
        
        result = 0;
        cnt = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                arr[i][j]=0;
                visited[i][j]=false;
            }
        }
//        cab.clear();
        
        for (int i=0;i<k;i++){
            cin>>x>>y;
            arr[y][x]=1;
            cab.push_back({y,x});
        }
        
        
        for (int i=0;i<cab.size();i++){
            if (!visited[cab[i].first][cab[i].second]){
                dfs(cab[i].first, cab[i].second);
                result++;
//                cout<<cab[i].first<<" "<<cab[i].second<<"**\n";
            }
            if (cnt == k)
                break;
        }
        cout<<result<<"\n";
    }
}

