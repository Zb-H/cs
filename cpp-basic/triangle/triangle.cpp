
#include <iostream>
using namespace std;

int main(){

    //enter 3 numbers figure out if these numbers can construct a triangle
    double num[3];
    bool result = false;
    cout << "num #1: ";
    cin >> num[0];
    cout << "num #2: ";
    cin >> num[1];
    cout << "num #3: ";
    cin >> num[2];

    cout << num[0] << endl;
    cout << num[1] << endl;
    cout << num[2] << endl;

    if(num[0] > num[1] + num[2] || num[1] > num[0] + num[2] || num [2] > num [0] + num[1]){
        result = true;
    }
    if(result){
        cout << "these three numbers can construct a  triangle.";
    }else{
        cout << "these three numbers can not construct a triangle.";
    }
    return 0;
}
