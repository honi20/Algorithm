#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
#include <queue>
#include <tuple>
using namespace std;
typedef long long ll;
typedef tuple<int,int,int> tii;
#define MAX 1005
#define INF 987654321

vector <int> v[MAX];
int cap[MAX][MAX] = {0,};   // 용량
int flow[MAX][MAX] = {0,};  // 현재 유량
int pre[MAX] = {0,};               // 경로를 기억하는 역할
int cost[MAX][MAX] = {0,};         // 노드에서 노드로 이동하는데 필요한 비용
int dist[MAX] = {0,};              // source 노드에서 해당 번호의 노드로 이동하는데 필요한 비용

int costSum = 0;            // 최소 비용
int totalFlow = 0;          // 최대 유량
int result_cnt = 0;

void add_edge(int from, int to, int c, int x){
    v[from].push_back(to);
    v[to].push_back(from);
    cap[from][to] = c;
    cap[to][from] = 0;
    cost[from][to] = x;
    cost[to][from] = -x;
}

void mcmf(int source, int sink){
    memset(flow, 0, sizeof(flow));
    
    while (1){
        queue<int> q;
        bool isInQ[MAX];
        
        memset(pre, -1, sizeof(pre));
        memset(isInQ, false, sizeof(isInQ));
        
        // dist 배열 초기화
        for (int i=0; i<MAX; i++)
            dist[i] = INF;
        
        q.push(source);
        pre[source] = source;
        dist[source] = 0;
        isInQ[source] = true;
        
        while (!q.empty()){
            int now = q.front();
            q.pop();
            isInQ[now] = false;
            
            for (int i = 0; i < v[now].size(); i++){
                int next = v[now][i];
                
                // 방문하지 않은 정점 중 용량이 남는 경우
                if (cap[now][next] - flow[now][next] > 0 && dist[now] + cost[now][next] < dist[next]){
                    pre[next] = now;
                    dist[next] = dist[now] + cost[now][next];
                    
                    if (!isInQ[next]){
                        q.push(next);
                        isInQ[next] = true;
                    }
                }
            }
        }
        
        // 더 이상 증가 경로 없음
        if (pre[sink] == -1)
            break;
        
        // 증가 경로로 새로 흘려줄 유량 = 경로 중 최소 잔여 용량
        int minFlow = INF;
        for (int i = sink; i != source; i = pre[i]){
            int j = pre[i];
            minFlow = min(cap[j][i] - flow[j][i], minFlow);
        }
        
        // 증가 경로는 유량 증가, 역방향 경로는 유량 감소
        for (int i = sink; i != source; i = pre[i]){
            int j = pre[i];
            flow[j][i] += minFlow;
            flow[i][j] -= minFlow;
            costSum += minFlow * cost[j][i];
        }
    }
    return;
}


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, m, x;
    int source = 1001, sink = 1002;
    int bias = 100;
    
    cin>>n>>m;
    for (int i=1; i<=n; i++){
        cin>>x;
        add_edge(bias+i, sink, x, 0);
    }
    
    for (int i=1; i<=m; i++){
        cin>>x;
        add_edge(source, i, x, 0);
    }
    
    for (int i=1; i<=m; i++){
        for (int j=1; j<=n; j++){
            cin>>x;
            add_edge(i, bias+j, INF, x);
        }
    }
    mcmf(source, sink);
    cout<<costSum;
    return 0;
}

