#include <iostream>
#include <cstring>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    bool num[10001];
    int sum;
    memset(num,false,sizeof(num));
    
    for (int i=1;i<=10000;i++){
        sum=i;
        int temp=i;
        while (temp>0){
            sum+=(temp%10);
            temp/=10;
        }
        if (sum<=10000){
            num[sum]=true;
        }
    }
    
    for (int i=1;i<=10000;i++){
        if (!num[i])
            cout<<i<<'\n';
    }
}
