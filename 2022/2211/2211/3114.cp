#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 1502
#define INF 987654321

int r, c;
string arr[MAX][MAX];

int dy[3] = {1, 0, 1};
int dx[3] = {0, 1, 1};
int cnt = 0;

void solve(int y, int x, vector <string> v){
    if (y == r-1 && x == c-1){
//        for (int i=0; i<v.size(); i++){
//            cout<<v[i]<<" ";
//        }
//        cout<<"*\n";
        cnt++;
        return;
    }
    
    for (int k=0; k<3; k++){
        int ny = y + dy[k];
        int nx = x + dx[k];
        
        if (ny < 0 || nx < 0 || ny >= r || nx >= c)
            continue;
        
        vector <string> v1 = v;
        v1.push_back(arr[ny][nx]);
        solve(ny, nx, v1);
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>r>>c;
    for (int i=0; i<r; i++){
        for (int j=0; j<c; j++){
            cin>>arr[i][j];
        }
    }
    vector <string> v;
    v.push_back(arr[0][0]);
    solve(0,0, v);
    cout<<cnt;
}

