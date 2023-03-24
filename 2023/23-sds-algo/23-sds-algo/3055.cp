#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
#include <queue>
#include <tuple>
using namespace std;
typedef long long ll;
typedef tuple<int, int, int> tii;
#define MAX 10002
#define INF 987654321

int n, m;
char arr[52][52];
int dpwater[52][52];
int dptime[52][52];

int starty, startx, desty, destx;
vector <pair<int,int>> water;

int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

void solve(int y, int x){
    for (int k=0; k<4; k++){
        int nexty = y + dy[k];
        int nextx = x + dx[k];
        
        if (nexty < 0 || nexty >= n || nextx < 0 || nextx >= m)
            continue;
        
        if (arr[nexty][nextx] == 'X' || arr[nexty][nextx] == 'D')
            continue;
        
        if (dpwater[y][x] + 1 < dpwater[nexty][nextx]){
            dpwater[nexty][nextx] = dpwater[y][x] + 1;
            solve(nexty, nextx);
        }
    }
}

int bfs(int y, int x){
    priority_queue <tii, vector <tii>, greater<>> q;    // time, y, x
    q.push({0, y, x});
    dptime[y][x] = 0;
    
    while (!q.empty()){
        int nowtime = get<0>(q.top());
        int nowy = get<1>(q.top());
        int nowx = get<2>(q.top());
        q.pop();
        
        if (nowy == desty && nowx == destx)
            return nowtime;
        
        for (int k=0; k<4; k++){
            int nexty = nowy + dy[k];
            int nextx = nowx + dx[k];
            
            if (nexty < 0 || nexty >= n || nextx < 0 || nextx >= m)
                continue;
            
            if (arr[nexty][nextx] == 'X')
                continue;
            
            if (dptime[nowy][nowx] + 1 < dpwater[nexty][nextx] && dptime[nowy][nowx] + 1 < dptime[nexty][nextx]){
                dptime[nexty][nextx] = dptime[nowy][nowx] + 1;
                q.push({dptime[nexty][nextx], nexty, nextx});
            }
        }
    }
    
    return -1;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0; i<n; i++){
        for (int j=0; j<m; j++){
            cin>>arr[i][j];
            if (arr[i][j] == 'D'){
                desty = i; destx = j;
            }
            else if (arr[i][j] == 'S'){
                starty = i; startx = j;
            }
            else if (arr[i][j] == '*'){
                water.push_back({i, j});
            }
            dpwater[i][j] = INF;
            dptime[i][j] = INF;
        }
    }
    
    for (int k=0; k<water.size(); k++){
        dpwater[water[k].first][water[k].second] = 0;
        solve(water[k].first, water[k].second);
    }
    
    int tmp = bfs(starty, startx);
    if (tmp == -1)
        cout<<"KAKTUS";
    else
        cout<<tmp;
}

