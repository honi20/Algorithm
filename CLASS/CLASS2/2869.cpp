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

    ll a,b,v;
    ll result=1;
    cin>>a>>b>>v;
    
    v=v-a;
    if (v>0){
        if (a-b == 1 || v%(a-b)==0){
            result += (v/(a-b));
        }
        else{
            result += (v/(a-b)+1);
        }
    }
    cout<<result;
}

/*반례 : 5 2 10439 (3479)*/
