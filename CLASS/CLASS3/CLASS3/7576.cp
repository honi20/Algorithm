#include <iostream>
#include <queue>
#include <vector>
#include <tuple>
using namespace std;

int m,n;
int arr[1002][1002];
int cnt=0;
bool flag = false;
vector <pair<int,int>> tyes;
priority_queue< pair<int,pair<int,int>>, vector<pair<int,pair<int,int>>> , greater<pair<int,pair<int,int>>>> q;

int dy[4] = {1,-1,0,0};
int dx[4] = {0,0,1,-1};

void bfs(){
    for (int i=0;i<tyes.size();i++){
        q.push({0,{tyes[i].first,tyes[i].second}});
    }
    
    while (!q.empty()){
        int nday = q.top().first;
        int ny = q.top().second.first;
        int nx = q.top().second.second;
        q.pop();
        
        for (int k=0;k<4;k++){
            int nexty = ny + dy[k];
            int nextx = nx + dx[k];
            
            if (nexty<0 || nexty>=n || nextx<0 || nextx>=m)
                continue;;
            
            if (arr[nexty][nextx] == 0){
                arr[nexty][nextx] = 1;
                cnt--;
                if (cnt == 0){
                    cout<<nday+1;
                    flag=true;
                    return;
                }
                q.push({nday+1, {nexty, nextx}});
            }
        }
        
    }
    
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>m>>n;
    for (int i=0;i<n;i++){
        for (int j=0;j<m;j++){
            cin>>arr[i][j];
            if (arr[i][j] == 0)
                cnt++;
            
            else if (arr[i][j] == 1)
                tyes.push_back({i,j});
        }
    }
    if (cnt == 0){
        cout<<"0";
        return 0;
    }
    bfs();
    if (!flag)
        cout<<"-1";
}
