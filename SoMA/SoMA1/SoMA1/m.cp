#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
#define MAX 987654321

int n, total;
int arr[25] = {0,};
int result = 0;

void solve(int idx, int sum){
    if (idx > n)
        return;
    
    if (sum + arr[idx] == total)
        result++;
    
    solve(idx+1, sum+arr[idx+1]);
    solve(idx+1, sum);
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>total;
    for (int i=1; i<=n; i++){
        cin>>arr[i];
    }
    
    solve(0,0);
    
    cout<<result;
    return 0;
}

/*
 -7 -3 -2 5 8
 -7 -10 -12 -7 1
 */
