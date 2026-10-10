#include Clock.h

Clock::Clock()
    : hour_(0), minute_(0){}

Clock::Clock(int hour, int minute)
    : hour_(hour), minutes_(minutes){}

int Clock::getHour() const {
    return hour_;
}

int Clock::getMinute() const {
    return minute_;
}

void Clock::incremetMinutes(int minutes){
    int totalminutes = hour _ * 60 + minutes_ + minutes;
    totalminutes %= 24 * 60;
    hour_ = totalMinutes /60;
    minutes_  = totalMinutes %60;
}
