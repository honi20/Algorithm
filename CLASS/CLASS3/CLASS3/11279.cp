#include <iostream>
#include <queue>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,x;
    priority_queue <int> q;
    
    cin>>n;
    while (n--){
        cin>>x;
        if (x == 0){
            if (q.empty())
                cout<<"0\n";
            else{
                cout<<q.top()<<"\n";
                q.pop();
            }
        }
        else{
            q.push(x);
        }
    }
}
