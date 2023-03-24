//
//  11866.cpp
//  CLASS3
//
//  Created by 최예헌 on 2022/07/11.
//

#include <iostream>
#include <queue>
using namespace std;

int main(int argc, const char * argv[]) {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,k;
    cin>>n>>k;
    
    queue<int> q;
    for (int i=1;i<=n;i++)
        q.push(i);
    
    cout<<"<";
    while (!q.empty()){
        if (q.size() == 1){
            cout<<q.front()<<">";
            q.pop();
        }
        
        else{
            for (int j=0;j<k-1;j++){
                q.push(q.front());
                q.pop();
            }
            
            cout<<q.front()<<", ";
            q.pop();
        }
    }
    
    return 0;
}
