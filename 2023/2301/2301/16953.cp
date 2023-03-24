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

ll a, b;

void solve(){
    priority_queue <pii, vector<pii>, greater<>> q;     // 최소횟수, 숫자
    q.push({1, a});
    
    while (!q.empty()){
        ll nowCnt = q.top().first;
        ll nowNum = q.top().second;
        q.pop();
        
        if (nowNum == b){
            cout<<nowCnt;
            return;
        }
        
        if (nowNum * 2 <= b){
            q.push({nowCnt+1, nowNum * 2});
        }
        
        if (nowNum * 10 + 1 <= b){
            q.push({nowCnt+1, nowNum * 10 + 1});
        }
    }
    
    cout<<"-1";
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>a>>b;
    solve();
}


//    a -> b
//    1. *2
//    2. 1을 수의 가장 오른쪽에 추가
//    최솟값 + 1 출력

/*
 2 162 -> 5
 // 2->4->8->81->162
 
2 4 8 16
 
 
 4 42 -> -1
 100 40021 -> 5
 // 100->200->2001->4002->40021
 */
