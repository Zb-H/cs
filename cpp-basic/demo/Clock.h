#ifndef CLOCK_H_ // HEADDER GUARD if not definded then define,if defineded, then return, prevent dupilication
#define CLOCK_H_ // define

class Clock{
    public: 
        Clock(); // constructor 1 default
        Clock(int hour, int minute); // constructor 2 w/ args

        // getter fuctions
        int getHour() const;
        int getMinute() const;
        // class functions
        void incrementMinutes(int minutes);
    
    // data field cant access from out side
    private:
        int hour_;
        int minutes_;
};

#endif
