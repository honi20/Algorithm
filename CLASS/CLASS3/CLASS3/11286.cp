#include <iostream>
#include <queue>
using namespace std;

struct cmp{
    bool operator()(string a, string b){
        if (a.size() == b.size()){
            return a>b;
        }
        return a.size()>b.size();
    }
};


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    string x;
    // 양수
    priority_queue<string, vector<string>, cmp> q1;   // 오름차순
    priority_queue<string, vector<string>, cmp> q2;     // 내림차순
    
    cin>>n;
    while (n--){
        cin>>x;
        if (x == "0"){
            if (q1.empty() && q2.empty())
                cout<<"0\n";

            else{
                if (q1.empty() && !q2.empty()){
                    cout<<"-"<<q2.top()<<"\n";
                    q2.pop();
                }
                else if (!q1.empty() && q2.empty()){
                    cout<<q1.top()<<"\n";
                    q1.pop();
                }
                else{
                    if (q1.top().size() == q2.top().size()){
                        
                        if (q1.top() >= q2.top()){
                            cout<<"-"<<q2.top()<<"\n";
                            q2.pop();
                        }
                        else{
                            cout<<q1.top()<<"\n";
                            q1.pop();
                        }
                    }
                    else if (q1.top().size() < q2.top().size()){
                        cout<<q1.top()<<"\n";
                        q1.pop();
                    }
                    else{
                        cout<<"-"<<q2.top()<<"\n";
                        q2.pop();
                    }
                }
            }
            
        }
        else{
            if (x[0] == '-'){
                q2.push(x.substr(1,x.size()-1));
            }
            else{
                q1.push(x);
            }
        }
    }
}
