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
    
    stack<char> s1, s2;
    string str;
    int n;
    char x;
    
    cin>>str;
    for (int i=0; i<str.size(); i++)
        s1.push(str[i]);
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>str;
        if (str == "L"){
            if (!s1.empty()){
                s2.push(s1.top());
                s1.pop();
            }
        }
        
        else if (str == "D"){
            if (!s2.empty()){
                s1.push(s2.top());
                s2.pop();
            }
        }
        
        else if (str == "B"){
            if (!s1.empty()){
                s1.pop();
            }
        }
        
        else if (str == "P"){
            cin>>x;
            s1.push(x);
        }
    }
    
    while (!s1.empty()){
        s2.push(s1.top());
        s1.pop();
    }
    
    while (!s2.empty()){
        cout<<s2.top();
        s2.pop();
    }
}

