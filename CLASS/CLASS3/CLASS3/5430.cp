#include <iostream>
#include <deque>
#include <vector>
#include <cmath>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t, n, front;
    string p, arr;
    
    cin>>t;
    while (t--){
        cin>>p>>n>>arr;
        deque <int> dq;
        front = 0;
        
        for (int i=0;i<arr.size();i++){
            if (arr[i] >= '0' && arr[i] <= '9'){
                int temp = 0;
                while (arr[i] >= '0' && arr[i] <= '9'){
                    temp = temp*10 + (int)(arr[i]-'0');
                    i++;
                }
                dq.push_back(temp);
            }
        }
        
        bool flag = true;
        for (int i=0;i<p.size();i++){
            if (p[i] == 'R'){
                front = abs(front-1);
            }
            else if (p[i] == 'D'){
                if (dq.empty()){
                    flag=false;
                    break;
                }
                
                if (front == 0){
                    dq.pop_front();
                }
                else if (front == 1){
                    dq.pop_back();
                }
                
            }
        }
        if (!flag)
            cout<<"error";
        
        else{
            cout<<"[";
            if (front == 0){
                for (int i=0;i<dq.size();i++){
                    if (i == 0)
                        cout<<dq[i];
                    else{
                        cout<<","<<dq[i];
                    }
                }
            }
            else{
                for (int i=dq.size()-1;i>=0;i--){
                    if (i == dq.size()-1)
                        cout<<dq[i];
                    else{
                        cout<<","<<dq[i];
                    }
                }
            }
            
            cout<<"]";
        }
        cout<<"\n";
    }
    
}
