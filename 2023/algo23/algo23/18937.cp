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

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n, a;
    ll tmp = 0;
    string str;
    
    cin>>n;
    while (n--){
        cin>>a;
        tmp ^= (a-2);
    }
    cin>>str;
    if (str[0] != 'W' && tmp == 0)
        cout<<"Whiteking";
    else if (str[0] == 'W' && tmp != 0)
        cout<<"Whiteking";
    else
        cout<<"Blackking";
}

