#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;

int solve(int n){
    int temp=0;
    while (n>0){
        temp+=(n%10);
        n/=10;
    }
    return temp;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n;
    cin>>n;
    
    for (int i=0;i<=n;i++){
        if (solve(i)+i==n){
            cout<<i;
            return 0;
        }
    }
    cout<<"0";
}
