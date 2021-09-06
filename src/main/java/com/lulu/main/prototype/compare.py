import threading
import psutil

def monitorMemory():
    proc = psutil.Process()
    print(proc.memory_info().rss)

def monitorCPU():
    print(psutil.cpu_percent())

def runTestScript(path_to_script):
    # test script running code
    print("running")

if __name__ == "__main__":
    thread1 = threading.Thread(target=monitorMemory)
    thread2 = threading.Thread(target=monitorCPU)
    thread3 = threading.Thread(target=runTestScript, args=("path",))
    threads = [thread1, thread2, thread3]
    for thread in threads:
        thread.start()
