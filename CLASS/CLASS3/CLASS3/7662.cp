#include <iostream>
#include <queue>
using namespace std;
typedef pair<int,int> pii;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t,n, y;
    char x;
    
    cin>>t;
    while (t--){
        priority_queue<pii> q1;     // 내림차순
        priority_queue<pii, vector<pii>, greater<pii>> q2;      // 오름차순
        bool check[1000002];
        
        cin>>n;
        for (int i=0;i<=n;i++)
            check[i]=false;
        
        for (int i=0;i<n;i++){
            cin>>x>>y;
            if (x == 'I'){
                q1.push({y,i});
                q2.push({y,i});
            }
            
            else if (x == 'D'){
                if (y == 1 && !q1.empty()){
                    while (check[q1.top().second]){
                        q1.pop();
                        if (q1.empty())
                            break;
                    }
                    check[q1.top().second]=true;
                    if (!q1.empty())
                        q1.pop();
                }
                
                else if (y == -1 && !q2.empty()){
                    while (check[q2.top().second]){
                        q2.pop();
                        if (q2.empty())
                            break;
                    }
                    check[q2.top().second]=true;
                    if (!q2.empty())
                        q2.pop();
                }
            }
        }
        
        while (check[q1.top().second] && !q1.empty()){
            q1.pop();
        }
        while (check[q2.top().second] && !q2.empty()){
            q2.pop();
        }
        
        if (q1.empty() || q2.empty())
            cout<<"EMPTY\n";
        else
            cout<<q1.top().first<<" "<<q2.top().first<<"\n";
        
        
    }
    
}
