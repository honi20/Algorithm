#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, x;
    stack <int> ss;
    vector <int> num;
    vector <string> result;
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>x;
        num.push_back(x);
    }
    
    int idx = 0;
    int tmp = 1;
    
    while (true){
        if (tmp > n)
            break;
        
        if (idx >= n)
            break;
        
        ss.push(tmp);
        result.push_back("+");
        tmp++;
        
        if (!ss.empty() && ss.top() == num[idx]){
            while (!ss.empty() && ss.top() == num[idx] && idx < n){
                ss.pop();
                result.push_back("-");
                idx++;
            }
        }
    }
    
    if (idx < n || !ss.empty())
        cout<<"NO";
    
    else{
        for (int i=0; i<result.size(); i++)
            cout<<result[i]<<"\n";
    }
    
    return 0;
}
