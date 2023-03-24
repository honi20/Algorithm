#include <iostream>
#include <queue>
#include <vector>
#include <tuple>
using namespace std;

int m,n,h;
int arr[102][102][102] = {0,};
int total = 0;
priority_queue < pair<int,tuple<int,int,int>> , vector<pair<int,tuple<int,int,int>>> , greater< pair<int,tuple<int,int,int>>>> q;


int dh[6] = {1,-1,0,0,0,0};
int dy[6] = {0,0,1,-1,0,0};
int dx[6] = {0,0,0,0,1,-1};
bool flag = false;

void bfs(){
    
    while (!q.empty()){
        int nowday = q.top().first;
        int nowh = get<0>(q.top().second);
        int nowy = get<1>(q.top().second);
        int nowx = get<2>(q.top().second);
        q.pop();
        
        for (int k=0;k<6;k++){
            int nexth = nowh + dh[k];
            int nexty = nowy + dy[k];
            int nextx = nowx + dx[k];
            
            if (nexth < 0 || nexth >= h || nexty < 0 || nexty >= n || nextx < 0 || nextx >= m)
                continue;
            
            if (arr[nexth][nexty][nextx] == 0){
                arr[nexth][nexty][nextx] = 1;
                total--;
                if (total == 0){
                    flag=true;
                    cout<<nowday+1;
                    return;
                }
                q.push({nowday+1, {nexth, nexty, nextx}});
            }
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>m>>n>>h;
    for (int i=0;i<h;i++){
        for (int j=0;j<n;j++){
            for (int k=0;k<m;k++){
                cin>>arr[i][j][k];
                
                if (arr[i][j][k] == 0)
                    total++;
                
                else if (arr[i][j][k] == 1){
                    q.push({0,{i,j,k}});
                }
            }
        }
    }
    if (total == 0){
        cout<<"0";
        return 0;
    }
    
    bfs();
    if (!flag)
        cout<<"-1";
}
