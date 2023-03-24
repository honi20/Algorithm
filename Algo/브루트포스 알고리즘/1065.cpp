#include <iostream>
using namespace std;

bool check(int n);

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
   
    int n;
    int cnt=0;
    
    cin>>n;
    
    if (n<10){
        cout<<n;
        return 0;
    }
    
    for (int i=10;i<=n;i++){
        if (check(i))
            cnt++;
    }
    
    cout<<cnt+9;
    
}

bool check(int n){
    int num[4];
    int cnt=0;
    int d;
    bool flag=true;
    
    for (int i=0;n>0;i++){
        num[i]=n%10;
        n/=10;
        cnt++;
    }
    d=num[1]-num[0];
    
    for (int i=1;i<cnt;i++){
        if (num[i]-num[i-1]!=d){
            flag=false;
            break;
        }
    }
    
    if (flag)
        return true;
    
    else
        return false;
    
}
