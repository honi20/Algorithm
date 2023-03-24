#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string str;
    ll result = 0;
    stack<pair<ll,ll>> s;
    
    cin>>str;
    
    for (ll i=0; i<str.size(); i++){
        if (str[i] == '('){
            if (s.empty())
                s.push({0, i});
            else
                s.push({s.top().first + 1, i});
        }
        
        else if (str[i] == ')'){
            if (!s.empty()){
                if (s.top().second + 1 == i){
                    result += s.top().first;
                    s.pop();
                }
                else
                    s.pop();
            }
        }
    }
    cout<<result;
}

