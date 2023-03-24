#include <iostream>
#include <cmath>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    int m, x;
    bool num[10]={false,};
    int result=1000002;
    
    cin>>n>>m;
    
    for (int i=0;i<10;i++)
        num[i]=true;

    for (int i=0;i<m;i++){
        cin>>x;
        num[x]=false;
    }
    
    result = min(result, abs(100-n));
    
    if (num[0]){
        result = min(result, 1+n);
    }
    
    for (int i=1;i<=1000002; i++){
        bool flag = false;  // 고장난 버튼 여부 확인
        int temp = i;
        int cnt = 0;

        while (temp>0){
            if (!num[temp%10]){
                flag = true;
                break;
            }
            cnt++;
            temp/=10;
        }
        
        if (flag)
            continue;

        if (!flag){
            result = min(result, cnt+abs(n-i));
        }
    }
    cout<<result;
}
