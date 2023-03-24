#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <cctype>
#include <stack>
#include <deque>
using namespace std;
typedef long long ll;


int main(){
    cin.tie(0); cout.tie(0);
    ios_base::sync_with_stdio(false);
    
    stack<int> s;
    int n,x;
    int result=0;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>x;
        if (x==0){
            s.pop();
        }
        else
            s.push(x);
    }
    
    while (!s.empty()){
        result += s.top();
        s.pop();
    }
    cout<<result;
}
