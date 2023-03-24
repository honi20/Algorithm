#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, x;
    priority_queue <int, vector<int>, greater<int>> q;
    
    cin>>n;
    for (int i=0;i<n;i++){
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

 
