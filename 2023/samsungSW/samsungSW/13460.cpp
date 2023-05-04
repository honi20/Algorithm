//
//  13460.cpp
//  samsungSW
//
//  Created by 최예헌 on 2023/04/05.
//  17:15 ~
//
#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
#include <queue>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int di[4] = {-1,1,0,0};
int dj[4] = {0,0,-1,1};

int n, m;
char arr[12][12];
int redi, redj,bluei, bluej, hi, hj;

bool visited[12][12] = {false,};
int group[12][12];

void dfs(int i, int j, int tmp){
    group[i][j] = tmp;
    visited[i][j] = true;
    
    for (int k=0; k<4; k++){
        int nexti = i + di[k];
        int nextj = j + dj[k];
        
        if (nexti < 0 || nextj < 0 || nexti >= n || nextj >= m)
            continue;
        
        if (arr[nexti][nextj] == '#')
            continue;
        
        if (visited[nexti][nextj])
            continue;
        
        dfs(nexti, nextj, tmp);
    }
    return;
}

vector<pii> getIdx(int dir, int ri, int rj, int bi, int bj){
    vector<pii> result;
    int resultri, resultrj, resultbi, resultbij;
    
    if (rj == bj){
        // 빨간 공 먼저
        if ((dir == 0 && ri < bi) || (dir == 1 && ri > bi)){
            int tmp = 0;
            while (true){
                int nextri = ri + di[dir] * tmp;
                int nextrj = rj + di[dir] * tmp;
                
                if (nextri < 0 || nextrj < 0 || nextri >= n || nextrj >= m)
                    continue;
                
                if (arr[nextri][nextrj] == '#')
                    break;
                
                resultri = nextrj; resultrj = nextri;
                
                if (arr[nextri][nextrj] == 'O'){
                    
                }
            }
        }
        
        // 파란 공 먼저
        else if ((dir == 0 && ri > bi) || (dir == 1 && ri < bi)){
            
        }
        
        // 같이
        else{
            
        }
    }
    
    else if (ri == bi){
        // 빨간 공 먼저
        if ((dir == 2 && rj < bj) || (dir == 3 && rj > bj)){
            
        }
        
        // 파란 공 먼저
        else if ((dir == 2 && rj > bj) || (dir == 3 && rj < bj)){
            
        }
        
        // 같이
        else{
            
        }
    }
    
    else{
        // 같이
    }
    
    return result;
}

void solve(int cnt, int dir, int ri, int rj, int bi, int bj){
    if (cnt > 10){
        cout<<"-1";
        return;
    }
    
    if (ri == hi && rj == hj){
        if (bi != hi || bj != hj)
            cout<<cnt;
        
        return;
    }
    
    for (int k=0; k<4; k++){
        if ((dir == 0 && k == 1) || (dir == 1 && k == 0) || (dir == 2 && k == 3) || (dir == 3 && k == 2))
            continue;
        
        pair<pii,pii> rbidx = getIdx(k, ri, rj, bi, bj);
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0; i<n; i++){
        for (int j=0; j<m; j++){
            cin>>arr[i][j];
            
            if (arr[i][j] == 'R'){
                redi = i; redj = j;
            }
            else if (arr[i][j] == 'B'){
                bluei = i; bluej = j;
            }
            else if (arr[i][j] == 'O'){
                hi = i; hj = j;
            }
        }
    }
    
    int tmp = 1;
    for (int i=0; i<n; i++){
        for (int j=0; j<m; j++){
            if (arr[i][j] != '#' && !visited[i][j]){
                dfs(i, j, tmp);
                tmp++;
            }
        }
    }
    
    if (group[redi][redj] != group[hi][hj]){
        cout<<"-11";
        return 0;
    }
    
    else{
        solve(0,-1,redi,redj,bluei,bluej);
    }
    
    
}

