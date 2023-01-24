#outputFile = open("data/FullPath.txt", "a", 0):

print('Change data/FullPath.txt')
print('Suggested:')
print('files\\\\testFile.txt')
path = input('Enter changes: ')

# writing to file sync
with open('data/FullPath.txt', 'w') as writer:
  writer.write(path)