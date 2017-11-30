import os
import subprocess

# this is the method automatically update the setting.py
# for adding a new app to the project
def rewriteInstallApp(newAppName):
    file = open("Macrosoft/settings.py", "r")
    list = [x for x in file]
    file.close()
    anotherFile = open("Macrosoft/settings.py", "w")

    for line in list:
        anotherFile.write(line + "")
        if "INSTALLED_APPS" in line:
            anotherFile.write("'"+newAppName+"'" + ",\n")
    anotherFile.close()


print("\nThis is a script which can help you do some operation about his project")

loop = True

# this is a infinite loop will keeping asking user what operation
# they want to do with this build script
# unless the user press a button by following the instruction to stop the loop
while(loop):
    print("only choice a number from below.")
    print("type number 1: start a an new app in this \n")
    print("type number 2: run the local server\n")
    print("type number 3: make some change to you model.py file in the app, need make migration?\n")
    print("type number 4: do the test in one button!\n")
    print("type numebr 5: update the project from the github\n ")
    print("type number 6: push your update to the github\n")
    print("type other irrevalent thing: I need to quit\n")
    a = input("Please input you number of choice: ")

    # help a user to create a new app
    # and it also will help user to automatically edit the
    # setting.py file
    if int(a) == 1:
        print("let's start a new app")
        appName = input("Please input the app name you want: ")
        args = ["django-admin", "startapp", appName]
        a = subprocess.run(args)
        if(a.returncode == 0):
            rewriteInstallApp(appName)
            print("add success\n")
        else:
            print("there might be some error\n")

    # help user to start the local server
    elif int(a) == 2:
        print("let's run the local server")
        args = ["python", "manage.py", "runserver"]
        a = subprocess.run(args)
        if(a.returncode == 0):
            print("successfully run the local server.\n")
        else: # if the default port 8000 is occupied, try another one 8080
            args.append("8080")
            b = subprocess.run(args)
            if(b.returncode == 0):
                print("success start the server, but the port number is", args[3], "instead of 8000")
            else:
                print("maybe error happen, sorry.")

    elif int(a) == 3:
        print("you make some change to the model.py? let's deal with that")
        # I will finish that later

    # help user autometically run the test script in the test.py
    elif int(a) == 4:
        print("we will do the test")
        args = ["python", "manage.py", "test"]
        process = subprocess.run(args)
        if(process == 0):
            print("test is run successfully\n")
        input()

    # help user automatically run the command git pull
    # in order to get the recent update from the git hub
    elif int(a) == 5:
        print("let us update the directory from the github!")
        args = ["git", "pull"]
        process = subprocess.run(args)
        if(process == 0):
            print("git pull successful!")
        else:
            input("failed press to continue")
            raise Exception("git pull not success, try to read the error information above.")

    # help user automatically run the comman git push
    # in order to push the recent committed change to remote github server
    elif int(a) == 6:
        print("congrduation, let us push the update to github server.")
        args = ["git", "push"]
        process = subprocess.run(args)
        if(process == 0):
            print("git push success")
        else:
            input("failed press to continue")
            raise Exception("git push failed, try to read the error information above.")

    # user choose to stop the loop
    # changing the loop boolean variable and end the loop
    else:
        print("have a good day!\n")
        loop = False

print("Enjoy coding!")
input()