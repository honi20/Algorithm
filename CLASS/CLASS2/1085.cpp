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

    int x,y,w,h;
    cin>>x>>y>>w>>h;
    
    int a = min(x,w-x);
    int b = min(y,h-y);
    cout<<min(a,b);
}
