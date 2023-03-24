#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    long long n;
    cin>>n;
    long long temp = n/5;
    long long result = temp;
    while (temp>0){
        temp/=5;
        result+=temp;
    }
    cout<<result;
}
