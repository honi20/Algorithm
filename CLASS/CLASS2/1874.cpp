//
//  1874.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,x;
    vector<int> v1;
    vector<int> v2;
    vector<int> result;
    stack<int> s;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>x;
        v1.push_back(x);
        v2.push_back(x);
    }
    sort(v2.begin(), v2.end());
    
    int idx = 0;
    for (int i=0;i<v2.size();i++){
        s.push(v2[i]);
        result.push_back(1);
        
        if (!s.empty()){
            while (s.top() == v1[idx]){
                s.pop();
                result.push_back(0);
                idx++;
                
                if (s.empty() || idx >= n)
                    break;
            }
        }
    }
    
    if (!s.empty())
        cout<<"NO";
    
    else{
        for (int i=0;i<result.size();i++){
            if (result[i] == 1)
                cout<<"+\n";
            else
                cout<<"-\n";
        }
    }

}
