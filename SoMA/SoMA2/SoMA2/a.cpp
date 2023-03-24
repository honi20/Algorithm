#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <cstring>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

vector <int> Fail(string search){
    vector<int> Pi(search.length());
    
    for (int i=1, j=0; i<search.length(); i++){
        while (j>0 && search[i]!=search[j])
            j=Pi[j-1];
        if (search[i] == search[j])
            Pi[i]=++j;
    }
    return Pi;
}

vector <int> KMP(string text, string search){
    vector<int> Pi = Fail(search);
    vector<int> Pos;
    
    for (int i=0,j=0; i<text.length(); i++){
        while (j>0 && text[i]!=search[j])
            j=Pi[j-1];
        if (text[i] == search[j]){
            if (j == search.length()-1){
                Pos.push_back(i-j);
                j=Pi[j];
            }
            else
                j++;
        }
    }
    return Pos;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string str1, str2;
    
    cin>>str1>>str2;
    
    vector <int> result = KMP(str1,str2);
    if (result.size() == 0)
        cout<<"0";
    else
        cout<<"1";
}
