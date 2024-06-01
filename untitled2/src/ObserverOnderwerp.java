public interface ObserverOnderwerp {
    void addObserver(BoekKastObserver observer);
    void removeObserver(BoekKastObserver observer);
    void notifyObservers();
}
