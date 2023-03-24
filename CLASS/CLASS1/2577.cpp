#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int A,B,C,result;
    int num[10]={0,};
    
    cin>>A>>B>>C;
    result=A*B*C;
    
    while (result>0){
        num[result%10]++;
        result/=10;
    }
    
    for (int i=0;i<10;i++)
        cout<<num[i]<<"\n";
}
