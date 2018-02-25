import tkinter as tk
from tkinter.filedialog import askopenfile
from mcdiscbuilder import build_resource_pack

def show_window():
    def go_button(entry):
        build_resource_pack(entry.get(), "build", ".")

    def choose_file(entry):
        entry.delete(0, tk.END)
        entry.insert(0, askopenfile().name)

    root = tk.Tk()
    tk.Label(root, text="mcdisc resource pack builder").grid(row=0, columnspan=4, column=0)
    tk.Label(root, text="disc list location:").grid(row=1, column=0)
    entry = tk.Entry(root)
    entry.grid(row=1, column=1)
    tk.Button(root, text="Choose File", command=lambda: choose_file(entry)).grid(row=1, column=2)
    tk.Button(root, text="Build the resource pack!", command=lambda: go_button(entry)).grid(row=1, column=3)

    root.mainloop()
