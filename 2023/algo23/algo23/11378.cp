#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <cstring>
#include <queue>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 2002
#define INF 987654321

vector <int> v[MAX];
int cap[MAX][MAX] = {0,};   // 용량
int flow[MAX][MAX] = {0,};  // 현재 유량
int pre[MAX] = {0,};        // 현재 탐색 중인 증가 경로에서 이전 정점을 저장하여 경로를 기억하는 역할
int total_flow = 0;         // 최대 유량

void maxFlow(int source, int sink){
    memset(flow, 0, sizeof(flow));
    
    while (1){
        queue<int> q;
        memset(pre, -1, sizeof(pre));
        
        q.push(source);
        pre[source] = source;
        
        while (!q.empty()){
            int now = q.front();
            q.pop();
            
            if (now == sink)
                break;
            
            for (int i=0; i<v[now].size(); i++){
                int next = v[now][i];
                
                // 방문하지 않은 정점 중 용량이 남는 경우
                if (cap[now][next] - flow[now][next] > 0 && pre[next] == -1){
                    q.push(next);
                    pre[next] = now;
                }
            }
        }
        
        // 더 이상 증가 경로 없음
        if (pre[sink] == -1)
            break;
        
        // 증가 경로로 새로 흘려줄 유량 = 경로 중 최소 잔여 용량
        int min_flow = INF;
        for (int i=sink; i!=source; i=pre[i]){
            int j = pre[i];
            min_flow = min(cap[j][i] - flow[j][i], min_flow);
        }
        
        // 증가 경로는 유량 증가, 역방향 경로는 유량 감소
        for (int i=sink; i!=source; i=pre[i]){
            int j = pre[i];
            flow[j][i] += min_flow;
            flow[i][j] -= min_flow;
        }
        
        total_flow += min_flow;
    }
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, m, k;
    int x, y;
    
    cin>>n>>m>>k;
    for (int i=0; i<n; i++){
        cin>>x;
        
        for (int j=0; j<x; j++){
            cin>>y;
            v[i+1].push_back(y + 1000);
            v[y+1000].push_back(i+1);
            cap[i+1][y+1000] = 1;
        }
    }
    maxFlow(0, 25);
    cout<<total_flow;
    return 0;
}

