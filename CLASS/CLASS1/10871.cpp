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

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,m,x;
    cin>>n>>m;
    while (n--){
        cin>>x;
        if (x<m)
            cout<<x<<" ";
    }
}
