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

int n;
int arr[20][20];
int result = 0;

void solve(int type, int i, int j){
    if (i == n && j == n){
        result++;
        return;
    }
    
    if (type == 1){     // 가로
        // 가로
        if (j < n && arr[i][j+1] != 1){
            solve(1, i, j+1);
        }
        
        // 대각선
        if (i < n && j < n && arr[i][j+1] != 1 && arr[i+1][j] != 1 && arr[i+1][j+1] != 1){
            solve(3, i+1, j+1);
        }
    }
    
    else if (type == 2){    // 세로
        // 세로
        if (i < n && arr[i+1][j] != 1){
            solve(2, i+1, j);
        }
        
        // 대각선
        if (i < n && j < n && arr[i][j+1] != 1 && arr[i+1][j] != 1 && arr[i+1][j+1] != 1){
            solve(3, i+1, j+1);
        }
    }
    
    else if (type == 3){    // 대각선
        // 가로
        if (j < n && arr[i][j+1] != 1){
            solve(1, i, j+1);
        }
        
        // 세로
        if (i < n && arr[i+1][j] != 1){
            solve(2, i+1, j);
        }
        
        // 대각선
        if (i < n && j < n && arr[i][j+1] != 1 && arr[i+1][j] != 1 && arr[i+1][j+1] != 1){
            solve(3, i+1, j+1);
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=1; i<=n; i++){
        for (int j=1; j<=n; j++){
            cin>>arr[i][j];
        }
    }
    
    solve(1,1,2);
    cout<<result;
}

