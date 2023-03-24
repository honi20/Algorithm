#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;
#define MAX 1000000001

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n;
    cin>>n;
    
    for (int i=n/5;i>=0;i--){
        if ((n - i*5)%3 == 0){
            cout<<i+(n - i*5)/3;
            return 0;
        }
    }
    cout<<"-1";
}
