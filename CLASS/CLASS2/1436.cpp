//
//  1436.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    cin>>n;
    
    int cnt=0;
    for (int i=666;i<=666666666;i++){
        int temp = i;
        
        while (temp > 0){
            int n1 = temp%10;
            temp/=10;
            
            if (n1 == 6 && temp > 0){
                int n2 = temp%10;
                temp/=10;
                
                if (n2 == 6 && temp > 0){
                    int n3 = temp%10;
                    temp/=10;
                    
                    if (n3 == 6){
                        cnt++;
                        break;
                    }
                }
            }
        }
        if (cnt == n){
            cout<<i;
            return 0;
        }
    }
}

