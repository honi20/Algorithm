#include <iostream>
#include <cstring>
#include <vector>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string str;
    vector <ll> v;
    ll sum=0;
    
    cin>>str;
    for (int i=0;i<str.size();i++){
        if (str[i] >= '0' && str[i] <= '9'){
            ll temp = 0;
            while (str[i] >= '0' && str[i] <= '9'){
                temp = temp*10 + (int)(str[i]-'0');
                i++;
            }
            i--;
            sum+=temp;
        }
        else if (str[i] == '-'){
            v.push_back(sum);
            sum=0;
        }
    }
    v.push_back(sum);
    
    ll result = v[0];
    for (int i=1;i<v.size();i++)
        result-=v[i];
    
    cout<<result;
}

