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

int di[4] = {-1,1,0,0};
int dj[4] = {0,0,-1,1};

int N, L, R;
int arr[52][52] = {0,};
bool visited[52][52] = {true,};
int day = 0;

void dfs(int i, int j, vector <pair<int,int>> group){
    bool flag = false;  // 갈 수 있는 공간 없음
    
    for (int k=0; k<4; k++){
        int nexti = i + di[k];
        int nextj = j + dj[k];
        
        if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N)
            continue;
        
        if (visited[nexti][nextj])
            continue;
        
        if (abs(arr[i][j] - arr[nexti][nextj]) < L || abs(arr[i][j] - arr[nexti][nextj]) > R)
            continue;
        
        visited[nexti][nextj] = true;
        dfs(nexti, nextj);
    }
    
    if (flag){
        for (int i=0; i<)
    }
    return;
}

void solve(){
    while (true){
        int cnt = 0;   // 더 이상 인구 이동 없음
        
        memset(visited, false, sizeof(false));
        
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if (!visited[i][j]){
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        
        if (cnt == N*N)
            break;
        day++;
    }
}
int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>N>>L>>R;
    for (int i=0; i<N; i++){
        for (int j=0; j<N; j++)
            cin>>arr[i][j];
    }
    
    solve();
}

