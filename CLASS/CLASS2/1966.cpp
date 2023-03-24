//
//  1966.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
#include <queue>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t,n,m,x;
    cin>>t;
    while (t--){
        cin>>n>>m;
        queue<pair<int,int>> q;
        for (int i=0;i<n;i++){
            cin>>x;
            q.push({i,x});
        }
        
        int result=1;
        while (!q.empty()){
            int now_idx = q.front().first;
            int now_imp = q.front().second;
            q.pop();
            
            bool check = true;
            long long qsize = q.size();
            
            if (q.empty() && now_idx == m){
                cout<<result<<"\n";
                break;
            }
            
            for (int i=0;i<qsize;i++){
                if (now_imp < q.front().second)
                    check=false;
                
                q.push(q.front());
                q.pop();
            }
            
            if (check){
                if (now_idx == m){
                    cout<<result<<"\n";
                    break;
                }
                result++;
            }
            
            else{
                q.push({now_idx,now_imp});
            }
        }
        
    }
}
