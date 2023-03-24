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

int dp[10002][3] = {0,};    // [0] : 키 , [1] : 왼자식 , [2] : 오자식
int parNode[10002];

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, tmp = 1;
    
    memset(parNode, -1, sizeof(parNode));
    while (cin >> n){
        dp[tmp][0] = n;
        
    }
    
}
/*
전위 순회한 결과 -> 후위 순회한 결과
 루트 왼 오 -> 왼 오 루트
*/

50
30
24
5 28
