/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int storageSize = 0;

    void clear() {
        for (Resume r : storage)
            r = null;
        storageSize = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storageSize; i++)
            if (storage[i].uuid.equals(r.uuid))
                return;
        if (storageSize < 10000) {
            storage[storageSize] = r;
            storageSize++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storageSize; i++)
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storageSize; i++)
            if (storage[i].uuid.equals(uuid)) {
                storageSize--;
                if (storageSize - i >= 0) System.arraycopy(storage, i + 1, storage, i, storageSize - i);
                return;
            }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[storageSize];
        if (storageSize >= 0) System.arraycopy(storage, 0, res, 0, storageSize);
        return res;
    }

    int size() {
        return storageSize;
    }
}
