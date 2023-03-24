#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

int n;
bool visited[40][40];

int dy[4] = {0,0,1,-1};
int dx[4] = {1,-1,0,0};
double per[4];
double result = 0;

void solve(int nowy, int nowx, int cnt, double persum){
    if (cnt == n){
        result += persum;
//        cout<<persum<<"*";
        return;
    }
    
    for (int k=0; k<4; k++){
        int nexty = nowy + dy[k];
        int nextx = nowx + dx[k];
        double nextper = per[k] / 100;
        
        if (visited[nexty][nextx])
            continue;
        
        visited[nexty][nextx] = true;
        solve(nexty, nextx, cnt+1, persum*nextper);
        visited[nexty][nextx] = false;
    }
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=0; i<4; i++)
        cin>>per[i];
    
    int nowy = 20, nowx = 20;
    visited[nowy][nowx] = true;
    
    for (int k=0; k<4; k++){
        int nexty = nowy + dy[k];
        int nextx = nowx + dx[k];
        double nextper = per[k] / 100;
        
        visited[nexty][nextx] = true;
        solve(nexty, nextx, 1, nextper);
        visited[nexty][nextx] = false;
    }
    cout<<fixed;
    cout.precision(10);
    cout<<result;
}
