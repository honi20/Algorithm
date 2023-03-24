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
#define INF 1000000000

string str;
ll n, x;
vector <string> order;
vector <ll> num;
stack <ll> s;

void solve(){
    ll idx = 0;    // NUM의 인덱스
    
    for (int i=0; i<order.size(); i++){
        if (order[i] == "NUM"){
            s.push(num[idx]);
            idx++;
        }
        
        else if (order[i] == "POP"){
            if (s.empty()){
                cout<<"ERROR\n";
                return;
            }
            s.pop();
        }
        
        else if (order[i] == "INV"){
            if (s.empty()){
                cout<<"ERROR\n";
                return;
            }
            ll tmp = s.top();
            s.pop();
            tmp *= (-1);
            s.push(tmp);
        }
        
        else if (order[i] == "DUP"){
            if (s.empty()){
                cout<<"ERROR\n";
                return;
            }
            s.push(s.top());
        }
        
        else {
            if (s.size() < 2){
                cout<<"ERROR\n";
                return;
            }
            
            ll tmp1 = s.top();
            s.pop();
            ll tmp2 = s.top();
            s.pop();
            
            if (order[i] == "SWP"){
                s.push(tmp1);
                s.push(tmp2);
            }
            
            else if (order[i] == "ADD"){
                if (abs(tmp1 + tmp2) > INF){
                    cout<<"ERROR\n";
                    return;
                }
                s.push(tmp1 + tmp2);
            }
            
            else if (order[i] == "SUB"){
                if (abs(tmp2 - tmp1) > INF){
                    cout<<"ERROR\n";
                    return;
                }
                s.push(tmp2 - tmp1);
            }
            
            else if (order[i] == "MUL"){
                if (abs(tmp1 * tmp2) > INF){
                    cout<<"ERROR\n";
                    return;
                }
                s.push(tmp1 * tmp2);
            }
            
            else if (order[i] == "DIV"){
                if (tmp1 == 0 || abs(tmp2 / tmp1) > INF){
                    cout<<"ERROR\n";
                    return;
                }
                ll result_tmp = abs(tmp2)/abs(tmp1);
                if (tmp1 * tmp2 >= 0){
                    s.push(result_tmp);
                }
                else{
                    s.push(result_tmp * (-1));
                }
            }
            
            else if (order[i] == "MOD"){
                if (tmp1 == 0 || abs(tmp2 % tmp1) > INF){
                    cout<<"ERROR\n";
                    return;
                }
                ll result_tmp = abs(tmp2) % abs(tmp1);
                if (tmp2 < 0)
                    s.push(result_tmp * (-1));
                else
                    s.push(result_tmp);
            }
        }
    }
    
    if (s.size() != 1)
        cout<<"ERROR\n";
    
    else
        cout<<s.top()<<"\n";
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    while (true){
        cin>>str;
        
        if (str == "QUIT")
            break;
        
        else if (str == "END"){
            cin>>n;
            for (int i=0; i<n; i++){
                cin>>x;
                s.push(x);
                solve();
                
                // 스택 초기화
                while (!s.empty())
                    s.pop();
            }
            cout<<"\n";
            order.clear();
            num.clear();
        }
        
        else if (str == "NUM"){
            cin>>x;
            order.push_back(str);
            num.push_back(x);
        }
        
        else {
            order.push_back(str);
        }
    }
}

