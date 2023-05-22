#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
#include <queue>
#include <tuple>
using namespace std;
typedef long long ll;
typedef tuple<int,int,int> tii;
#define MAX 805
#define INF 987654321

vector <int> v[MAX];
int cap[MAX][MAX] = {0,};   // 용량
int flow[MAX][MAX] = {0,};  // 현재 유량
int pre[MAX];               // 경로를 기억하는 역할
int cost[MAX][MAX];         // 노드에서 노드로 이동하는데 필요한 비용
int dist[MAX];              // source 노드에서 해당 번호의 노드로 이동하는데 필요한 비용

int costSum = 0;            // 최소 비용
int totalFlow = 0;          // 최대 유량
int result_cnt = 0;

void add_edge(int from, int to, int c){
    v[from].push_back(to);
    v[to].push_back(from);
    cap[from][to] = c;
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
        totalFlow += minFlow;
        result_cnt++;
    }
    return;
}


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, m;
    int workCnt, workNum, pay;
    int source = 801, sink = 802;
    int bias = 400;
    
    cin>>n>>m;
    for (int i = 1; i <= n; i++){
        cin>>workCnt;
        add_edge(source, i, 1);
        
        while (workCnt--){
            cin>>workNum>>pay;
            add_edge(i, bias+workNum, 1);
            cost[i][bias+workNum] = pay;
            cost[bias+workNum][i] = -pay;
        }
    }
    
    for (int i = 1; i <= m; i++){
        add_edge(bias+i, sink, 1);
    }
    
    mcmf(source, sink);
    cout<<result_cnt<<"\n"<<costSum;
    return 0;
}

